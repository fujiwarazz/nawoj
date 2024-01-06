package com.yupi.nawoj_backend.mapper;

 import com.baomidou.mybatisplus.core.mapper.BaseMapper;
 import com.yupi.nawoj_backend.model.entity.QuestionSubmit;
 import org.apache.ibatis.annotations.Mapper;

/**
* @author 李鱼皮
* @description 针对表【question_submit(题目提交)】的数据库操作Mapper
* @createDate 2023-08-07 20:58:53
* @Entity com.yupi.yuoj.model.entity.QuestionSubmit
*/
@Mapper
public interface QuestionSubmitMapper extends BaseMapper<QuestionSubmit> {

}




