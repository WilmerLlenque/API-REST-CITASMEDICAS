package com.citasmedicas.web.controller;

import com.citasmedicas.persistence.entity.Patient;
import com.citasmedicas.persistence.entity.PatientPK;
import com.citasmedicas.persistence.entity.User;
import com.citasmedicas.services.PatientServices;
import com.citasmedicas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patient")
@CrossOrigin(origins = "http://localhost:4200")
public class PatientController {
    @Autowired
    private UserService userService;
    @Autowired
    private PatientServices patientServices;

    @GetMapping("all")
    public List<Patient> getPatient(){
        return patientServices.getPatient();
    }

    @GetMapping("/{idUser}/{idPatient}")
    public Optional<Patient> getPatientId(@PathVariable int idUser, @PathVariable int idPatient){
        return patientServices.getPatientId(idUser,idPatient);
    }
    public Optional<Patient> findPatientById(@RequestBody PatientPK patientPK){
        return patientServices.findPatientById(patientPK);
    }
    @PostMapping("/addPatient")
    public ResponseEntity<Patient> addUserWithPatient(@RequestBody Patient patientRequest){

        Integer countUser= (int) userService.countUser();
        Integer countPatient=(int) patientServices.countPatient()+1;

        User user=new User();
        user.setIdUser(countUser);
        user.setName(patientRequest.getUser().getName());
        user.setEmail(patientRequest.getUser().getEmail());
        user.setAddress(patientRequest.getUser().getAddress());
        user.setPhoneNumber(patientRequest.getUser().getPhoneNumber());

        Patient patient=new Patient();
        patient.setIdPatient(countPatient);
        patient.setIdUser(countUser);
        patient.setBirthday(patientRequest.getBirthday());
        patient.setWhight(patientRequest.getWhight());
        patient.setHeight(patientRequest.getHeight());
        patient.setBlood(patientRequest.getBlood());
        patient.setAvailable(true);

        patientServices.saveUserWithPatient(user,patient);

        return ResponseEntity.ok(patient);
    }
    @PutMapping("/updatePatient")
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patientRequest){

        Optional<User> optionalUser=userService.findById(patientRequest.getIdUser());
        Optional<Patient> optionalPatient=patientServices.getPatientId(patientRequest.getIdUser(),patientRequest.getIdPatient());

        if (optionalUser.isPresent() && optionalPatient.isPresent()){
            User user=optionalUser.get();
            user.setName(patientRequest.getUser().getName());
            user.setEmail(patientRequest.getUser().getEmail());
            user.setAddress(patientRequest.getUser().getAddress());
            user.setPhoneNumber(patientRequest.getUser().getPhoneNumber());

            Patient patient=optionalPatient.get();
            patient.setBirthday(patientRequest.getBirthday());
            patient.setWhight(patientRequest.getWhight());
            patient.setHeight(patientRequest.getHeight());
            patient.setBlood(patientRequest.getBlood());

            patientServices.saveUserWithPatient(user,patient);
            return ResponseEntity.ok(patient);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idUser}/{idPatient}")
    public ResponseEntity<Void> delete(@PathVariable Integer idUser,@PathVariable Integer idPatient){
        Optional<Patient> optionalPatient=patientServices.findPatientById(new PatientPK(idPatient,idUser));
        if (optionalPatient.isPresent()){
            Patient patient=optionalPatient.get();
            patient.setAvailable(false);
            patientServices.delete(patient);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}