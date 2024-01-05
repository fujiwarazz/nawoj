package com.yupi.nawoj_backend.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.nawoj_backend.common.enums.ErrorCode;
import com.yupi.nawoj_backend.constant.CommonConstant;
import com.yupi.nawoj_backend.exception.BusinessException;
import com.yupi.nawoj_backend.exception.ThrowUtils;

import com.yupi.nawoj_backend.mapper.QuestionMapper;
import com.yupi.nawoj_backend.mapper.QuestionMapper;
import com.yupi.nawoj_backend.mapper.UserMapper;
import com.yupi.nawoj_backend.model.dto.question.QuestionQueryRequest;
import com.yupi.nawoj_backend.model.entity.*;
import com.yupi.nawoj_backend.model.vo.QuestionVO;
import com.yupi.nawoj_backend.model.vo.QuestionVO;
import com.yupi.nawoj_backend.model.vo.UserVO;
import com.yupi.nawoj_backend.service.QuestionService;
import com.yupi.nawoj_backend.service.QuestionService;
import com.yupi.nawoj_backend.service.UserService;
import com.yupi.nawoj_backend.utils.SqlUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author peelsannaw
 * @create 31/12/2023 17:35
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

    @Resource
    private UserService userService;

    @Override
    public void validQuestion(Question question, boolean add) {

        if (question == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String title = question.getTitle();
        String content = question.getContent();
        String tags = question.getTags();
        String answer = question.getAnswer();
        String judgeCase = question.getJudgeCase();
        String judgeConfig = question.getJudgeConfig();

        // 创建时，参数不能为空
        if (add) {
            ThrowUtils.throwIf(StringUtils.isAnyBlank(title, content, tags), ErrorCode.PARAMS_ERROR);
        }
        // 有参数则校验
        if (StringUtils.isNotBlank(title) && title.length() > 80) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "标题过长");
        }
        if (StringUtils.isNotBlank(content) && content.length() > 8192) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "内容过长");
        }
        if (StringUtils.isNotBlank(answer) && answer.length() > 1024 * 1024) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "答案过长");
        }
        if (StringUtils.isNotBlank(judgeCase) && judgeCase.length() > 1024 * 1024) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "判题程序过长");
        }
        if (StringUtils.isNotBlank(judgeConfig) && judgeConfig.length() > 1024 * 1024) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "判题配置过长");
        }


    }

    /**
     * 获取查询包装类
     *
     * @param questionQueryRequest
     * @return
     */
    @Override
    public QueryWrapper<Question> getQueryWrapper(QuestionQueryRequest questionQueryRequest) {
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        if (questionQueryRequest == null) {
            return queryWrapper;
        }
        Long id = questionQueryRequest.getId();
        String title = questionQueryRequest.getTitle();
        String content = questionQueryRequest.getContent();
        List<String> tags = questionQueryRequest.getTags();
        Long userId = questionQueryRequest.getUserId();
        String userName = questionQueryRequest.getUserName();
        String sortField = questionQueryRequest.getSortField();
        String sortOrder = questionQueryRequest.getSortOrder();


        //拼接查询
        queryWrapper.like(StringUtils.isNotBlank(title), "title", title);
        queryWrapper.like(StringUtils.isNotBlank(content), "content", content);
        if (CollUtil.isNotEmpty(tags)) {
            for (String tag : tags) {
                queryWrapper.like("tags", "\"" + tag + "\"");
            }
        }
        queryWrapper.eq(ObjectUtils.isNotEmpty(id), "id", id);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }


    @Override
    public QuestionVO getQuestionVO(Question question, HttpServletRequest request) {

        QuestionVO questionVO = QuestionVO.objToVo(question);
        long QuestionId = question.getId();
        // 1. 关联查询用户信息
        Long userId = question.getUserId();
        User user = null;
        if (userId != null && userId > 0) {
            user = userService.getById(userId);
        }
        UserVO userVO = userService.getUserVO(user);
        questionVO.setUserVO(userVO);


        return questionVO;
    }


    @Override
    public Page<QuestionVO> getQuestionVOPage(Page<Question> QuestionPage, HttpServletRequest request) {
        List<Question> QuestionList = QuestionPage.getRecords();
        Page<QuestionVO> QuestionVOPage = new Page<>(QuestionPage.getCurrent(), QuestionPage.getSize(), QuestionPage.getTotal());
        if (CollUtil.isEmpty(QuestionList)) {
            return QuestionVOPage;
        }
        // 1. 关联查询用户信息
        Set<Long> userIdSet = QuestionList.stream().map(Question::getUserId).collect(Collectors.toSet());
        Map<Long, List<User>> userIdUserListMap =
                userService
                        .listByIds(userIdSet)
                        .stream()
                        .collect(Collectors.groupingBy(User::getId));
        List<QuestionVO> QuestionVOList = QuestionList
                .stream()
                .map(Question -> {
                    QuestionVO questionVO = QuestionVO.objToVo(Question);
                    Long userId = Question.getUserId();
                    User user = null;
                    if (userIdUserListMap.containsKey(userId)) {
                        user = userIdUserListMap.get(userId).get(0);
                    }
                    questionVO.setUserVO(userService.getUserVO(user));
                    return questionVO;
                }).collect(Collectors.toList());
        QuestionVOPage.setRecords(QuestionVOList);
        return QuestionVOPage;
    }
}
