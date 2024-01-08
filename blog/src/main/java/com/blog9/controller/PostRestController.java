package com.blog9.controller;

import com.blog9.payload.PostDto;
import com.blog9.payload.PostResponse;
import com.blog9.service.SavePostImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/post")
@SpringBootApplication
public class PostRestController {
    SavePostImpl savePostImpl;

    public PostRestController(SavePostImpl savePostImpl) {

        this.savePostImpl = savePostImpl;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> savePostControllerMethod(@Valid @RequestBody PostDto postDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto postDtoReturn=savePostImpl.createPost(postDto);

        return new ResponseEntity<>(postDtoReturn, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteData(@PathVariable long id){
        savePostImpl.deleteById(id);
        return new ResponseEntity<>("Post is deleted",HttpStatus.OK);
    }
    @PreAuthorize("hasRole('USER')")

    @GetMapping("{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable long id){
        PostDto postDto=savePostImpl.getPostById(id);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updatePost(@PathVariable long id,@RequestBody PostDto postDto){
        savePostImpl.updatePostById(id,postDto);
        return new ResponseEntity<>("post updated",HttpStatus.OK);
    }
//    @GetMapping
//    public List<PostDto> getAllPost(){
//        List<PostDto> allPostDto=savePostImpl.getAllPost();
//        return allPostDto;
//    }
    //http://localhost:8080/api/post?pageNo=0&pageSize=5&sortBy=title

    @GetMapping
public PostResponse getAllPost(
        @RequestParam(name="pageNo",required=false,defaultValue = "0") int pageNo,
        @RequestParam(name="pageSize",required=false,defaultValue = "5") int pageSize,
        @RequestParam(value="sortBy", required=false,defaultValue = "id") String sortBy,
        @RequestParam(value="sortDir", required=false,defaultValue = "asc") String sortDir
){
    PostResponse allPostResponse=savePostImpl.getAllPost(pageNo,pageSize,sortBy,sortDir);
    return allPostResponse;
}
}
