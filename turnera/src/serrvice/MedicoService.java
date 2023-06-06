package serrvice;

import dao.DAOMedico;
import entidades.Medico;

public class MedicoService {
    private DAOMedico daoMedico;
        public MedicoService(){
            daoMedico = new DAOMedico();
        }
        public void guardarMedico(Medico medico) throws ServiceException {
            try {
                daoMedico.guardar(medico);
            } catch (Exception e) {
                throw new ServiceException(e.getMessage());
            }
        }




}
