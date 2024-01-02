package com.yupi.nawoj_backend.model.dto.question;

import lombok.Data;

import java.util.List;

/**
 * 题目用例
 */
@Data
public class QuestionJudgeCase {

    /**
     * 输入用例
     */
    private String input;

    /**
     * 输出用例
     */
    private String output;
}
