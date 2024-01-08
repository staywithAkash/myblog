package com.blog9.service;
import com.blog9.payload.PostResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;

import com.blog9.entity.Post;
import com.blog9.exception.ResourceNotFound;
import com.blog9.payload.PostDto;
import com.blog9.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SavePostImpl implements SavePost {
    private PostRepository postRepo;
    private ModelMapper modelMapper;

    public SavePostImpl(PostRepository postRepo,ModelMapper modelMapper) {

        this.postRepo = postRepo;
        this.modelMapper=modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postdto){
        Post post= mapToEntity(postdto);
        postRepo.save(post);
        PostDto mappedDto=mapToDto(post);
        return mappedDto;
    }
    @Override
    public void deleteById(long id){
        Post post=postRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFound("ID is not found"+id)
        );
        postRepo.deleteById(id);
    }

    @Override
    public PostDto getPostById(long id) {
        Post post=postRepo.findById(id).orElseThrow(
                ()->new ResourceNotFound("Post not found with id "+id)
        );
        return mapToDto(post);
    }

    public void updatePostById(long id, PostDto postDto) {
        Post post=postRepo.findById(id).orElseThrow(
                ()->new ResourceNotFound("Post not found")
        );
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        post.setId(post.getId());
        postRepo.save(post);
    }

    @Override
    public PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {
        PostResponse response=new PostResponse();

        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(pageNo,pageSize,sort);
        Page<Post> pageOfPost=postRepo.findAll( pageable);
        List<Post> pagePostList=pageOfPost.getContent();
        List<PostDto> pagePostDto=pagePostList.stream().map(e->mapToDto(e)).collect(Collectors.toList());

        response.setPosts(pagePostDto);
        response.setPageNo(pageOfPost.getNumber());
        response.setPageSize(pageOfPost.getSize());
        response.setTotalPages(pageOfPost.getTotalPages());
        response.setLastPage(pageOfPost.isLast());
        return response;
    }

    public PostDto mapToDto(Post post) {

//        PostDto postDto=new PostDto();
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());
          PostDto postDto=modelMapper.map(post,PostDto.class);
        return postDto;
    }

    private Post mapToEntity(PostDto postdto) {
//        Post EntityObj=new Post();
//        EntityObj.setTitle(postdto.getTitle());
//        EntityObj.setDescription(postdto.getDescription());
//        EntityObj.setContent(postdto.getContent());
        Post EntityObj=modelMapper.map(postdto,Post.class);
        return EntityObj;
    }

}
