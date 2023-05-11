package Tony.Flowers.group.services;

import Tony.Flowers.group.entity.Imagee;
import Tony.Flowers.group.exceptions.MiException;
import Tony.Flowers.group.repositories.ImageRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

    @Autowired
    public ImageRepository imageRepository;

    public Imagee submitImg(MultipartFile archive) throws MiException {
        if (archive != null) {
            try {
                Imagee image = new Imagee();
                image.setMime(archive.getContentType());
                image.setName(archive.getName());
                image.setContainer(archive.getBytes());

                return imageRepository.save(image);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    public Imagee updateImg(MultipartFile archive, String idImage) {
        if (archive != null) {
            try {
                Imagee image = new Imagee();

                if (idImage != null) {
                    Optional<Imagee> response = imageRepository.findById(idImage);

                    if (response.isPresent()) {
                        image = response.get();
                    }
                }

                image.setMime(archive.getContentType());
                image.setName(archive.getName());
                image.setContainer(archive.getBytes());

                return imageRepository.save(image);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

}
