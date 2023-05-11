package Tony.Flowers.group.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Tony.Flowers.group.entity.Imagee;
        
@Repository
public interface ImageRepository extends JpaRepository<Imagee,String>{
    
}
