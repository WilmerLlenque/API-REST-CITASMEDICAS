package com.citasmedicas.web.controller;

import com.citasmedicas.persistence.entity.Nurse;
import com.citasmedicas.persistence.entity.NursePK;
import com.citasmedicas.persistence.entity.User;
import com.citasmedicas.services.NurseService;
import com.citasmedicas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/nurse")
@CrossOrigin(origins = "http://localhost:4200")
public class NurseController {

    @Autowired
    private UserService userService;
    @Autowired
    private NurseService nurseService;

    @GetMapping("all")
    public List<Nurse> getNurses(){
        return nurseService.getNurses();
    }

    @GetMapping("/{idUser}/{idNurse}")
    public Optional<Nurse> getNurseId(@PathVariable int idUser,@PathVariable int idNurse){
        return nurseService.getNurseId(idUser,idNurse);
    }
    public Optional<Nurse> findNurseById(@RequestBody NursePK nursePK){
        return nurseService.findNurseById(nursePK);
    }
    @PostMapping("/addNurse")
    public ResponseEntity<Nurse> saveNurse(@RequestBody Nurse nurseRequest){

        Integer countUser= (int) userService.countUser();
        Integer countNurse=(int) nurseService.countNurse()+1;

        User user=new User();
        user.setIdUser(countUser);
        user.setName(nurseRequest.getUser().getName());
        user.setEmail(nurseRequest.getUser().getEmail());
        user.setAddress(nurseRequest.getUser().getAddress());
        user.setPhoneNumber(nurseRequest.getUser().getPhoneNumber());

        Nurse nurse=new Nurse();
        nurse.setIdNurse(countNurse);
        nurse.setIdUser(user.getIdUser());
        nurse.setSpeciality(nurseRequest.getSpeciality());
        nurse.setAvailable(true);

        nurseService.saveUserWithNurse(user,nurse);
        return ResponseEntity.ok(nurse);
    }
    @PutMapping("/updateNurse")
    public ResponseEntity<Nurse> updateNurse(@RequestBody Nurse nurseRequest){

        Optional<User> optionalUser=userService.findById(nurseRequest.getIdUser());
        Optional<Nurse> optionalNurse=nurseService.getNurseId(nurseRequest.getIdUser(),nurseRequest.getIdNurse());
        if (optionalUser.isPresent() && optionalNurse.isPresent()){
            User user=optionalUser.get();
            user.setName(nurseRequest.getUser().getName());
            user.setEmail(nurseRequest.getUser().getEmail());
            user.setAddress(nurseRequest.getUser().getAddress());
            user.setPhoneNumber(nurseRequest.getUser().getPhoneNumber());

            Nurse nurse=optionalNurse.get();
            nurse.setSpeciality(nurseRequest.getSpeciality());

            nurseService.saveUserWithNurse(user,nurse);
            return ResponseEntity.ok(nurse);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idUser}/{idNurse}")
    public ResponseEntity<Void> delete(@PathVariable Integer idUser,@PathVariable Integer idNurse){
        Optional<Nurse> optionalNurse=nurseService.findNurseById(new NursePK(idNurse,idUser));
        if (optionalNurse.isPresent()){
            Nurse nurse=optionalNurse.get();
            nurse.setAvailable(false);
            nurseService.delete(nurse);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
