package Tony.Flowers.group.services;

import Tony.Flowers.group.entity.Imagee;
import Tony.Flowers.group.entity.Userr;
import Tony.Flowers.group.enums.Rol;
import Tony.Flowers.group.exceptions.MiException;
import Tony.Flowers.group.repositories.UserRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageService imageService;

    @Transactional
    public void createUser(MultipartFile archive, String email, String password, String password2, String name) throws MiException {

        validations(email, password, password2, name);

        Userr userCreate = new Userr();

        userCreate.setEmail(email);
        userCreate.setPassword(new BCryptPasswordEncoder().encode(password));
        userCreate.setName(name);
        userCreate.setRol(Rol.USER);
        userCreate.setUpUser(new Date());

        Imagee image = imageService.submitImg(archive);
        userCreate.setIcon(image);

        userRepository.save(userCreate);
    }

    @Transactional
    public void updateUser(MultipartFile archive, Long id, String password, String name) throws MiException {

        Optional<Userr> findUser = userRepository.findById(id);

        if (findUser.isPresent()) {

            Userr user = findUser.get();

            if (new BCryptPasswordEncoder().matches(password, user.getPassword())) {

                if (name.isEmpty() || name == null || name.length() <= 2) {
                    throw new MiException("El nombre no puede estar vacio o ser menor a 2 caracteres");
                }
                user.setName(name);

                String idImage = null;

                if (user.getIcon() != null) {
                    idImage = user.getIcon().getId();
                }

                Imagee image = imageService.updateImg(archive, idImage);
                user.setIcon(image);

                userRepository.save(user);
            }
        }
    }

    public Userr getOne(Long id) {
        return userRepository.getOne(id);
    }

    public List<Userr> listUsers() {
        List<Userr> users = new ArrayList();
        users = userRepository.findAll();

        return users;
    }

    public void validations(String email, String password, String password2, String name) throws MiException {

        if (email.isEmpty() || email == null) {
            throw new MiException("No puede estar vacio el campo email");
        }

        if (password == null || password.isEmpty() || password.length() <= 4) {
            throw new MiException("La contraseña no puede estar vacia o ser menor a 4 caracteres");
        }

        if (!password.equals(password2)) {
            throw new MiException("Las contraseñas no coinciden");
        }

        if (name.isEmpty() || name == null || name.length() <= 2) {
            throw new MiException("El nombre no puede estar vacio o ser menor a 2 caracteres");
        }

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Userr user = userRepository.searchUserEmail(email);

        if (user != null) {

            List<GrantedAuthority> permissions = new ArrayList();

            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + user.getRol().toString());

            permissions.add(p);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

            HttpSession session = attr.getRequest().getSession(true);

            session.setAttribute("userSession", user);

            return new User(user.getName(), user.getPassword(), permissions);

        } else {
            return null;
        }
    }

}
