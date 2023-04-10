package uz.onlinestor.onlinestoreback.service.contragent;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.onlinestor.onlinestoreback.dto.CommentDto;
import uz.onlinestor.onlinestoreback.models.contragent.Comment;
import uz.onlinestor.onlinestoreback.repository.contragent.CommentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class CommentService {

    final CommentRepository commentRepository;

    public List<CommentDto> getAllbyProduct(Long id) {
        return getAllCommentDto(id);
    }

    private List<CommentDto> getAllCommentDto(Long id) {
        return commentRepository.
                getAllbyProduct(id).
                stream().
                map(this::convertToCommentDto).
                collect(Collectors.toList());
    }


    private CommentDto convertToCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
//        commentDto.setMark(comment());
        commentDto.setComment(comment.getComment());

        return commentDto;
    }

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}
