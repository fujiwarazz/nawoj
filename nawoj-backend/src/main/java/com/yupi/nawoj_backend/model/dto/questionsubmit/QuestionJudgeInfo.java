package com.yupi.nawoj_backend.model.dto.questionsubmit;

import lombok.Data;

/**
 * @Author peelsannaw
 * @create 31/12/2023 23:05
 * 判题信息
 */
@Data
public class QuestionJudgeInfo {

    /**
     * 程序执行信息
     */
    private String message;

    /**
     * 内存消耗(KB)
     */
    private Long memoryLimit;

    /**
     * 栈消耗(KB)
     */
    private Long stackLimit;
}
