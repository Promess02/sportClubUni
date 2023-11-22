package mikolaj.project.backendapp.controller;


import mikolaj.project.backendapp.DTO.NewsPostFilter;
import mikolaj.project.backendapp.DTO.NewsPostForm;
import mikolaj.project.backendapp.DTO.ServiceResponse;
import mikolaj.project.backendapp.service.NewsPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/newsPosts")
public class NewsPostController {
    private final NewsPostService newsPostService;

    @Autowired
    public NewsPostController(NewsPostService newsPostService) {
        this.newsPostService = newsPostService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllPosts(){
        ServiceResponse<?> response = newsPostService.viewAllNewsPost();
        if(response.getData().isEmpty()) return ResponseUtil.badRequestResponse(response.getMessage());
        return ResponseUtil.okResponse(response.getMessage(), "NewsPosts", response.getData());
    }

    @PostMapping("/addPost")
    public ResponseEntity<?> addPost(@RequestBody NewsPostForm newsPostForm){
        ServiceResponse<?> response = newsPostService.addNewsPost(newsPostForm);
        if(response.getData().isEmpty()) return ResponseUtil.badRequestResponse(response.getMessage());
        return ResponseUtil.okResponse(response.getMessage(), "NewsPost", response.getData());
    }

    @GetMapping("/getRecent")
    public ResponseEntity<?> getAllRecent(@RequestBody NewsPostFilter newsPostFilter){
        ServiceResponse<?> response = newsPostService.viewRecentNewsPosts(newsPostFilter.getYears(),
                newsPostFilter.getMonths(), newsPostFilter.getDays());
        if(response.getData().isEmpty()) return ResponseUtil.badRequestResponse(response.getMessage());
        return ResponseUtil.okResponse(response.getMessage(), "NewsPosts", response.getData());
    }

    @GetMapping("/getRecentForActivity")
    public ResponseEntity<?> getAllForActivity(@RequestBody NewsPostFilter newsPostFilter){
        ServiceResponse<?> response = newsPostService.viewPostsForActivity(newsPostFilter.getActivityId(), newsPostFilter.getYears(),
                newsPostFilter.getMonths(), newsPostFilter.getDays());
        if(response.getData().isEmpty()) return ResponseUtil.badRequestResponse(response.getMessage());
        return ResponseUtil.okResponse(response.getMessage(), "NewsPosts", response.getData());
    }

    @GetMapping("/getRecentForLocation")
    public ResponseEntity<?> getAllForLocation(@RequestBody NewsPostFilter newsPostFilter){
        ServiceResponse<?> response = newsPostService.viewPostsForLocation(newsPostFilter.getLocationId(), newsPostFilter.getYears(),
                newsPostFilter.getMonths(), newsPostFilter.getDays());
        if(response.getData().isEmpty()) return ResponseUtil.badRequestResponse(response.getMessage());
        return ResponseUtil.okResponse(response.getMessage(), "NewsPosts", response.getData());
    }

    @GetMapping("/getRecentForMembershipType")
    public ResponseEntity<?> getAllForMembershipType(@RequestBody NewsPostFilter newsPostFilter){
        ServiceResponse<?> response = newsPostService.viewPostsForMembershipType(newsPostFilter.getMembershipTypeId(), newsPostFilter.getYears(),
                newsPostFilter.getMonths(), newsPostFilter.getDays());
        if(response.getData().isEmpty()) return ResponseUtil.badRequestResponse(response.getMessage());
        return ResponseUtil.okResponse(response.getMessage(), "NewsPosts", response.getData());
    }

}
