package com.fundaciones.andrearodriguez.fundaciones.addperro;



/**
 * Created by andrearodriguez on 8/20/16.
 */
public class AddPerroInteractorImp implements AddPerroInteractor{

   AddPerroRepository repository;

   public AddPerroInteractorImp(AddPerroRepository repository) {
      this.repository = repository;
   }

   @Override
   public void excecute(String name, String edad, String sexo, String tamano, String path, String esteril, String vacuna, String discapacidad) {
      repository.uploadPhoto(name, edad, sexo, tamano, path, esteril, vacuna, discapacidad);
   }
}
