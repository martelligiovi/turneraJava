package service;

import dao.DAOAdmin;

public class AdminService {
    private DAOAdmin daoAdmin;
    public AdminService(){
        daoAdmin = new DAOAdmin();
    }

    public boolean login(String user, String password) throws ServiceException {
        try {
            return daoAdmin.login(user,password);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

}
