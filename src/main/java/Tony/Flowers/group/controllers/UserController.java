package Tony.Flowers.group.controllers;

import Tony.Flowers.group.entity.Userr;
import Tony.Flowers.group.exceptions.MiException;
import Tony.Flowers.group.services.UserService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/")
    public String index(HttpSession session, ModelMap model) {

        Userr log = (Userr) session.getAttribute("userSession");
        if (log.getRol().toString().equals("ADMIN")) {
            model.put("title", "Flowers | Inicio");
            return "redirect:/";
        }

        model.put("title", "Flowers | Inicio");

        return "index.html";
    }
    
    @GetMapping("/register")
    public String register(ModelMap model) {
        model.put("title", "Flowers | Registro");

        return "index.html";
    }

    @PostMapping("/registered")
    public String registered(MultipartFile archive,@RequestParam String email, @RequestParam String password, @RequestParam String password2, @RequestParam String name, ModelMap model) {

        try {
            userService.createUser(archive, email, password, password2, name);

            model.put("success", "Usuario registrado correctamente");
            model.put("title", "Flowers | Inicio");
            return "redirect:/";
        } catch (MiException ex) {

            model.put("error", ex.getMessage());
            model.put("name", ex.getMessage());
            model.put("email", ex.getMessage());
            model.put("password", ex.getMessage());

            return "index.html";
        }

    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap model) {
        model.put("title", "Flowers | Loguer");
        if (error != null) {
            model.put("error", "Usuario o contraseña invalidos");
        }
        return "index.html";
    }

}
