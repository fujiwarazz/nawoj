package com.yupi.nawoj_backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.nawoj_backend.mapper.QuestionMapper;
import com.yupi.nawoj_backend.mapper.QuestionSubmitMapper;
import com.yupi.nawoj_backend.model.entity.Question;
import com.yupi.nawoj_backend.model.entity.QuestionSubmit;
import com.yupi.nawoj_backend.service.QuestionService;
import com.yupi.nawoj_backend.service.QuestionSubmitService;
import org.springframework.stereotype.Service;

/**
 * @Author peelsannaw
 * @create 31/12/2023 17:36
 */
@Service
public class QuestionSubmitServiceImpl  extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit> implements QuestionSubmitService {
}
