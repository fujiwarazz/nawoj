package com.yupi.nawoj_backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.nawoj_backend.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.yupi.nawoj_backend.model.entity.Question;
import com.yupi.nawoj_backend.model.entity.QuestionSubmit;
import com.yupi.nawoj_backend.model.entity.User;

/**
 * @Author peelsannaw
 * @create 31/12/2023 17:34
 */
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    /**
     * 外部提交题目
     * @param questionId
     * @param loginUser
     * @return
     */
    Long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

    Long doQuestionSubmitInner(Long userId, QuestionSubmitAddRequest questionSubmitAddRequest);



}
