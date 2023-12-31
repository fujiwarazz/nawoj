package com.yupi.nawoj_backend.controller;

import com.yupi.nawoj_backend.common.BaseResponse;
import com.yupi.nawoj_backend.common.enums.ErrorCode;
import com.yupi.nawoj_backend.common.ResultUtils;
import com.yupi.nawoj_backend.exception.BusinessException;
import com.yupi.nawoj_backend.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.yupi.nawoj_backend.model.entity.User;
import com.yupi.nawoj_backend.service.QuestionSubmitService;
import com.yupi.nawoj_backend.service.UserService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 问题提交接口
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@RestController
@RequestMapping("/question_submit")
@Slf4j
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService QuestionSubmitService;

    @Resource
    private UserService userService;

    /**
     * 点赞 / 取消点赞
     *
     * @param QuestionSubmitAddRequest
     * @param request
     * @return resultNum 本次点赞变化数
     */
    @PostMapping("/")
    public BaseResponse<Integer> doThumb(@RequestBody QuestionSubmitAddRequest QuestionSubmitAddRequest,
            HttpServletRequest request) {
        if (QuestionSubmitAddRequest == null || QuestionSubmitAddRequest.getPostId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能点赞
        final User loginUser = userService.getLoginUser(request);
        long postId = QuestionSubmitAddRequest.getPostId();
        int result = QuestionSubmitService.doQuestionSubmit(postId, loginUser);
        return ResultUtils.success(result);
    }

}
