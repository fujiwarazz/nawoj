package com.yupi.nawoj_backend.model.vo;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.yupi.nawoj_backend.model.dto.question.QuestionJudgeCase;
import com.yupi.nawoj_backend.model.dto.question.QuestionJudgeConfig;
import com.yupi.nawoj_backend.model.entity.Question;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
* @Author peelsannaw
* @create 31/12/2023 17:38
*/
@Data
public class QuestionVO implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表（json 数组）
     */
    private List<String> tags;


    /**
     * 题目提交数
     */
    private Integer submitNum;

    /**
     * 题目通过数
     */
    private Integer acceptedNum;

    /**
     * 判题配置（json 对象）
     */
    private QuestionJudgeConfig judgeConfig;

    /**
     * 点赞数
     */
    private Integer thumbNum;

    /**
     * 收藏数
     */
    private Integer favourNum;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建题目者
     */
    private UserVO userVO;



    public static Question voToObj(QuestionVO questionVO) {
        if (questionVO == null) {
            return null;
        }
        Question question = BeanUtil.copyProperties(questionVO, Question.class,"tags","judgeConfig");
        List<String> tagList = questionVO.getTags();
        if (tagList != null) {
            question.setTags(JSON.toJSONString(tagList));
        }
        QuestionJudgeConfig judgeConfig = questionVO.getJudgeConfig();
        if(judgeConfig!=null){
            question.setJudgeConfig(JSON.toJSONString(judgeConfig));
        }
        return question;
    }

    /**
     * 对象转包装类
     *
     * @param question
     * @return
     */
    public static QuestionVO objToVo(Question question) {
        if (question == null) {
            return null;
        }
        QuestionVO questionVO = new QuestionVO();
        BeanUtils.copyProperties(question, questionVO);
        questionVO.setTags(JSON.parseArray(question.getTags(), String.class));
        questionVO.setJudgeConfig(JSON.parseObject(question.getJudgeConfig(), QuestionJudgeConfig.class));
        return questionVO;
    }

    private static final long serialVersionUID = 1L;



}
