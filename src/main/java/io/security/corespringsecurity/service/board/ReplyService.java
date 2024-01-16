package io.security.corespringsecurity.service.board;

import io.security.corespringsecurity.domain.entity.board.Reply;


import java.util.List;

public interface ReplyService {

     List<Reply> getAllPosts();

     Reply getPostById(Long postId);

//     List<Reply> getByCommentId(Long commentId);

     void savePost(Reply reply);

     void deletePost(Long postId);


}
