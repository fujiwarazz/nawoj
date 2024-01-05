package com.yupi.nawoj_backend.service.impl;

import co.elastic.clients.elasticsearch.xpack.usage.Sql;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.nawoj_backend.annotation.Prevent;
import com.yupi.nawoj_backend.common.enums.ErrorCode;
import com.yupi.nawoj_backend.exception.BusinessException;
import com.yupi.nawoj_backend.mapper.QuestionSubmitMapper;

import com.yupi.nawoj_backend.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.yupi.nawoj_backend.model.entity.*;
import com.yupi.nawoj_backend.model.enums.QuestionSubmitLanguageEnum;
import com.yupi.nawoj_backend.model.enums.QuestionSubmitStatusEnum;
import com.yupi.nawoj_backend.service.QuestionService;
import com.yupi.nawoj_backend.service.QuestionService;
import com.yupi.nawoj_backend.service.QuestionSubmitService;
import com.yupi.nawoj_backend.service.QuestionSubmitService;

import org.apache.ibatis.session.SqlSessionException;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author peelsannaw
 * @create 31/12/2023 17:36
 */
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit> implements QuestionSubmitService {


    @Resource
    private QuestionService questionService;

    /**
     * 题目提交
     *
     * @param loginUser
     * @return
     */
    @Override
    @Prevent(value = "3", message = "提交过快,请稍后")
    public Long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser) {
        // 判断实体是否存在，根据类别获取实体
        String language = questionSubmitAddRequest.getLanguage();
        QuestionSubmitLanguageEnum enumByValue = QuestionSubmitLanguageEnum.getEnumByValue(language);
        if(enumByValue==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"语言选择错误");
        }

        Question question = questionService.getById(questionSubmitAddRequest.getQuestionId());
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }

        // 是否已题目提交
        long userId = loginUser.getId();
        // 每个用户串行题目提交
        // 锁必须要包裹住事务方法
        QuestionSubmitService questionSubmitThumbService = (QuestionSubmitService) AopContext.currentProxy();
        //锁住常量池内的唯一用户ID
        synchronized (String.valueOf(userId).intern()) {
            return questionSubmitThumbService.doQuestionSubmitInner(userId, questionSubmitAddRequest);
        }
    }

    /**
     * 封装了事务的方法
     *
     * @param userId
     * @return
     */

    @Transactional
    @Override
    public Long doQuestionSubmitInner(Long userId, QuestionSubmitAddRequest questionSubmitAddRequest) {
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setUserId(userId);
        questionSubmit.setQuestionId(questionSubmitAddRequest.getQuestionId());
        questionSubmit.setCode(questionSubmitAddRequest.getCode());
        questionSubmit.setLanguage(questionSubmitAddRequest.getLanguage());
        questionSubmit.setStatus(QuestionSubmitStatusEnum.WAITING.getValue());
        questionSubmit.setJudgeInfo("{}");
        boolean save = this.save(questionSubmit);
        if(!save){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"题目提交失败");
        }else return questionSubmit.getId();
    }
}
