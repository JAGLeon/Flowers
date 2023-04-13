package Tony.Flowers.group.repositories;

import Tony.Flowers.group.entity.Userr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Userr,Long> {
    
    @Query("SELECT u FROM Userr u WHERE u.email = :email ")
    public Userr searchUserEmail(@Param("email") String email);
    
}
