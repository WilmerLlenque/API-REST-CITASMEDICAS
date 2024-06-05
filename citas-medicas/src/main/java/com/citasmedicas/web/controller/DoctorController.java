package com.citasmedicas.web.controller;

import com.citasmedicas.persistence.entity.Doctor;
import com.citasmedicas.persistence.entity.DoctorPK;
import com.citasmedicas.persistence.entity.User;
import com.citasmedicas.services.DoctorService;
import com.citasmedicas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/doctor")
@CrossOrigin(origins = "http://localhost:4200")
public class DoctorController {

    @Autowired
    private UserService userService;
    @Autowired
    private DoctorService doctorService;

    @GetMapping("all")
    public List<Doctor> getDoctors(){
        return doctorService.getDoctors();
    }

    @GetMapping("/{idUser}/{idDoctor}")
    public Optional<Doctor> getDoctorId(@PathVariable int idUser,@PathVariable int idDoctor){
        return doctorService.getDoctorId(idUser,idDoctor);
    }

    @GetMapping("/findDoctorById")
    public Optional<Doctor> findDoctorById(@RequestBody DoctorPK doctorPK){
        return doctorService.findDoctorById(doctorPK);
    }

    @PostMapping("/addDoctor")
    public ResponseEntity<Doctor> saveDoctor(@RequestBody Doctor doctorRequest){

        Integer countUser= (int) userService.countUser();
        Integer countDoctor=(int) doctorService.countDoctor()+1;

        User user=new User();
        user.setIdUser(countUser);
        user.setName(doctorRequest.getUser().getName());
        user.setEmail(doctorRequest.getUser().getEmail());
        user.setAddress(doctorRequest.getUser().getAddress());
        user.setPhoneNumber(doctorRequest.getUser().getPhoneNumber());

        Doctor doctor=new Doctor();
        doctor.setIdDoctor(countDoctor);
        doctor.setIdUser(countUser);
        doctor.setSpeciality(doctorRequest.getSpeciality());
        doctor.setAvailable(true);

        doctorService.saveUserWithDoctor(user,doctor);
        return ResponseEntity.ok(doctor);
    }
    @PutMapping("/updateDoctor")
    public ResponseEntity<Doctor> updateDoctor(@RequestBody Doctor doctorRequest){

        Optional<User> optionalUser=userService.findById(doctorRequest.getIdUser());
        Optional<Doctor> optionalDoctor=doctorService.getDoctorId(doctorRequest.getIdUser(),doctorRequest.getIdDoctor());
        if (optionalUser.isPresent() && optionalDoctor.isPresent()){
            User user=optionalUser.get();
            user.setName(doctorRequest.getUser().getName());
            user.setEmail(doctorRequest.getUser().getEmail());
            user.setAddress(doctorRequest.getUser().getAddress());
            user.setPhoneNumber(doctorRequest.getUser().getPhoneNumber());

            Doctor doctor=optionalDoctor.get();
            doctor.setSpeciality(doctorRequest.getSpeciality());

            doctorService.saveUserWithDoctor(user,doctor);
            return ResponseEntity.ok(doctor);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idUser}/{idDoctor}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Integer idUser,@PathVariable Integer idDoctor){

        Optional<Doctor> optionalDoctor=doctorService.findDoctorById(new DoctorPK(idDoctor,idUser));
        if (optionalDoctor.isPresent()){
            Doctor doctor=optionalDoctor.get();
            doctor.setAvailable(false);
            doctorService.delete(doctor);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
