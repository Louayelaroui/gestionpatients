package com.formationspringboot.gestionpatients.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.formationspringboot.gestionpatients.dao.PatientRepository;
import com.formationspringboot.gestionpatients.entites.Patient;
import org.springframework.beans.factory.annotation.Value;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ServicePatient  implements IServicePatient{
	

	PatientRepository pr;
	
	
	public ServicePatient(PatientRepository pr) {
		super();
		this.pr = pr;
	}

	@Override
	public void addPatient(Patient p,MultipartFile mf) throws IOException {
		
        if (!mf.isEmpty()) {
            String newImageName = saveImage2(mf);
            p.setMedicalImage(newImageName); // Set only the new image name
        }
        pr.save(p);
	
	}
	@Override
	public void addPatient(Patient p) {
	        pr.save(p);
	
	}

	@Override
	public void deletePatient(Long id) {
		
		pr.deleteById(id);
		
	}

	@Override
	public List<Patient> getAllPatients() {
		
		return pr.findAll();
	}

	@Override
	public List<Patient> getPatientsByName(String mc) {
		
		return pr.rechercheParCle(mc);
	}

	@Override
	public Patient getPatientById(Long id) {
		
		return pr.findById(id).get();
	}

	@Override
	public Page<Patient> getPatientsByName(String mc, Pageable p) {
		// TODO Auto-generated method stub
		return pr.findByNomContains(mc, p);
	}
	
    private String giveMeNewName(String oldName)
    {
        String firstpart= oldName.substring(0,oldName.lastIndexOf("."));
        String secondpart= oldName.substring(oldName.lastIndexOf(".")+1);
        return firstpart+System.currentTimeMillis()+"."+secondpart;
    }
   /* @Override
    public String savImage(MultipartFile mf) throws IOException {
        String nomFile=mf.getOriginalFilename();

        String newName=giveMeNewName(nomFile);
        File f= new ClassPathResource("static/images").getFile();
        String chemin=f.getAbsolutePath();
        Path p= Paths.get(chemin,newName);
        Files.write(p,mf.getBytes());
        return newName;
    }*/
@Value("${upload.dir}")
  private String uploadDir;

    private String saveImage2(MultipartFile mf) throws IOException {
       String newName=giveMeNewName(mf.getOriginalFilename());
        Path uploadPath=Paths.get(uploadDir);
       if(!Files.exists(uploadPath))
           Files.createDirectories(uploadPath);

       Path pathFile=uploadPath.resolve(newName);

        Files.write(pathFile,mf.getBytes());

        return newName;
    }

    @Override
    public byte[] getMedicalImage(Long id) throws IOException {

        return Files.readAllBytes(Paths.get(uploadDir,getPatientById(id).getMedicalImage()));
    }

    private String uploadImage(MultipartFile mf) throws IOException {
        String oldName=mf.getOriginalFilename();
        String tab[]=oldName.split("\\.");
        String newName=tab[0]+System.currentTimeMillis()+"."+tab[1];

        Path p=Paths.get(uploadDir,newName);

        Files.write(p,mf.getBytes());
        return newName;
    }

}
