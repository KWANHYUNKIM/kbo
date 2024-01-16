package io.security.corespringsecurity.service.impl.board;

import io.security.corespringsecurity.domain.entity.board.Reply;
import io.security.corespringsecurity.repository.board.ReplyRepository;
import io.security.corespringsecurity.service.board.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyRepository replyRepository;
    @Override
    public List<Reply> getAllPosts() {
        return replyRepository.findAll();
    }
    @Override
    public Reply getPostById(Long postId) {
        return replyRepository.findById(postId).orElse(null);
    }

//    @Override
//    public List<Reply> getByCommentId(Long commentId) {
//        return replyRepository.findByCommentId(commentId);
//    }

    @Override
    public void savePost(Reply reply) {
        replyRepository.save(reply);
    }
    @Override
    public void deletePost(Long postId) {
        replyRepository.deleteById(postId);
    }

}
