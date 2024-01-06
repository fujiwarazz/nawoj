package com.yupi.nawoj_backend.model.dto.question;

import lombok.Data;

/**
 * @Author peelsannaw
 * @create 06/01/2024 18:21
 * 问题输入输出描述 势力
 */

@Data
public class QuestionJudgeDescription {
    //输入格式
    String inputFormat;

    //输出格式
    String outputFormat;

    //数据范围
    String dataRange;

}
