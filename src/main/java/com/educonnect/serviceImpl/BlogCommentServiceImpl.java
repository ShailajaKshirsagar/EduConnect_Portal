package com.educonnect.serviceImpl;

import com.educonnect.dto.CommentRequestDto;
import com.educonnect.dto.CommentResponseDto;
import com.educonnect.entity.BlogComment;
import com.educonnect.entity.BlogPost;
import com.educonnect.entity.User;
import com.educonnect.repository.BlogCommentRepo;
import com.educonnect.repository.BlogPostRepo;
import com.educonnect.repository.UserRepo;
import com.educonnect.service.BlogCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BlogCommentServiceImpl implements BlogCommentService {

    @Autowired
    private BlogCommentRepo blogCommentRepo;

    @Autowired
    private BlogPostRepo blogPostRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public CommentResponseDto createComment(long postid, CommentRequestDto requestDto, String username) {

        BlogPost post  = blogPostRepo.findById(postid).orElseThrow(() -> new RuntimeException("Blog post not found"));

        User author  = userRepo.findByUsername(username);
        if(author==null){
            throw new RuntimeException("User not found");
        }

        BlogComment comment = BlogComment.builder()
                .post(post)
                .author(author)
                .content(requestDto.getContent())
                .build();

        BlogComment saved = blogCommentRepo.save(comment);

        return CommentResponseDto.builder()
                .comment_id(saved.getComment_id())
                .content(comment.getContent())
                .authorname(author.getUsername())
                .authorid(author.getId())
                .createdat(saved.getCreatedat())
                .build();
    }

    @Override
    public Page<CommentResponseDto> getCommentsByPostId(long postId, Pageable pageable) {

       Page<BlogComment> blogComments = blogCommentRepo.findByPostId(postId,pageable);

       return blogComments.map(comment -> CommentResponseDto.builder()
               .comment_id(comment.getComment_id())
               .authorname(comment.getAuthor().getUsername())
               .content(comment.getContent())
               .authorid(comment.getAuthor().getId())
               .createdat(comment.getCreatedat()).build());
    }
}
