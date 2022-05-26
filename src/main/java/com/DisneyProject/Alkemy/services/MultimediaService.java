
package com.DisneyProject.Alkemy.services;

import com.DisneyProject.Alkemy.dto.MultimediaDto;
import com.DisneyProject.Alkemy.dto.MultimediaResponse;


public interface MultimediaService {
    
     public void deleteMultimedia(long id);
      
      public MultimediaDto updateMultimedia(MultimediaDto multimediaDto, long id);
      
      public MultimediaDto crearMultimedia(MultimediaDto multimediaDto);
    
      public MultimediaResponse getAllMultimedia (int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir);

      public MultimediaResponse getByNombreOGenero (int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir, String nombre, String id );
      
      public void deletePersonajeMultimedia(long movieId, long charId);
      
      public void crearPersonajeMultimedia (long movieId, long charId);
}
