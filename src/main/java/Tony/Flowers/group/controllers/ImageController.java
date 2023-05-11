package Tony.Flowers.group.controllers;

import Tony.Flowers.group.entity.Userr;
import Tony.Flowers.group.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/image")
public class ImageController {
    
    @Autowired
    public UserService userService;
    
    @GetMapping("/profile/{id}")
    public ResponseEntity<byte[]> imageUser(@PathVariable Long id){
        Userr user = userService.getOne(id);
        byte[] imgUser = user.getIcon().getContainer();
        
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.IMAGE_JPEG);
        
        return new ResponseEntity<>(imgUser,headers,HttpStatus.OK);
    }
    
}
