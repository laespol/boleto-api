package br.com.artivinco.consultaboleto.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.artivinco.consultaboleto.model.Tbtoken;

public interface TbtokenRepository extends JpaRepository<Tbtoken, Long> {
	

	
	   @Query("SELECT t FROM Tbtoken t WHERE t.token = ?1 and  t.dtFinish > ?2 and t.status = ''")
		public Tbtoken findBytoken(String token, Date data);
   
	
}
