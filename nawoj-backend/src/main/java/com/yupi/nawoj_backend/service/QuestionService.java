package com.yupi.nawoj_backend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.yupi.nawoj_backend.model.dto.question.QuestionQueryRequest;
import com.yupi.nawoj_backend.model.entity.Question;
import com.yupi.nawoj_backend.model.entity.Question;
import com.yupi.nawoj_backend.model.entity.User;
import com.yupi.nawoj_backend.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author peelsannaw
 * @create 31/12/2023 17:34
 */
public interface QuestionService  extends IService<Question> {


    /**
     * 校验
     *
     * @param Question
     * @param add
     */
    void validQuestion(Question Question, boolean add);

    /**
     * 获取查询条件
     *
     * @param QuestionQueryRequest
     * @return
     */
    QueryWrapper<Question> getQueryWrapper(QuestionQueryRequest QuestionQueryRequest);



    /**
     * 获取帖子封装
     *
     * @param Question
     * @param request
     * @return
     */
    QuestionVO getQuestionVO(Question Question, HttpServletRequest request);

    /**
     * 分页获取帖子封装
     *
     * @param QuestionPage
     * @param request
     * @return
     */
    Page<QuestionVO> getQuestionVOPage(Page<Question> QuestionPage, HttpServletRequest request);
}
