package com.yupi.nawoj_backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.nawoj_backend.mapper.PostMapper;
import com.yupi.nawoj_backend.mapper.QuestionMapper;
import com.yupi.nawoj_backend.model.entity.Post;
import com.yupi.nawoj_backend.model.entity.Question;
import com.yupi.nawoj_backend.service.PostService;
import com.yupi.nawoj_backend.service.QuestionService;
import org.springframework.stereotype.Service;

/**
 * @Author peelsannaw
 * @create 31/12/2023 17:35
 */
@Service
public class QuestionServiceImpl  extends ServiceImpl<QuestionMapper, Question> implements QuestionService {
    
}
