package uz.onlinestor.onlinestoreback.resource.contragent;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.onlinestor.onlinestoreback.dto.CommentDto;
import uz.onlinestor.onlinestoreback.models.contragent.Comment;
import uz.onlinestor.onlinestoreback.service.contragent.CommentService;

import java.util.List;

@RestController
@RequestMapping("/online/doc/comment/")
@RequiredArgsConstructor
public class CommentResource {

    @Autowired
    final CommentService commentService;

    @GetMapping("getbyproduct")
    private List<CommentDto> getAll(@RequestParam("id") String id) {
        return commentService.getAllbyProduct(Long.parseLong(id));
    }



    @PostMapping("save")
    private Comment save(@RequestBody Comment comment) {
        return commentService.save(comment);
    }

    @DeleteMapping()
    private void delete(@RequestParam("id") String id) {
        commentService.delete(Long.parseLong(id));
    }


}
