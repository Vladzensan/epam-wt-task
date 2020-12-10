package services;

import dao.*;
import entity.CartEntity;
import entity.RoleEntity;
import entity.PianoEntity;
import entity.UserEntity;

import java.util.List;

public class Service {
    private final UserDao userDao = new UserDao();
    private final PianoDao pianoDao = new PianoDao();
    private final CartDao cartDao = new CartDao();

    public boolean signin(UserEntity userEntity) throws ServiceException {
        Filter filter = new Filter();
        filter.setEmail(userEntity.getEmail());
        UserEntity existingUser;

        try {
            existingUser = userDao.getList(filter).stream().findFirst().orElse(null);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        if (existingUser == null || !existingUser.getPassword().equals(userEntity.getPassword())) {
            return false;
        }

        userEntity.setId(existingUser.getId());
        userEntity.setName(existingUser.getName());
        userEntity.setRole(existingUser.getRole());

        return true;
    }

    public boolean signup(UserEntity user) throws ServiceException {
        Filter filter = new Filter();
        filter.setEmail(user.getEmail());
        UserEntity existingUser;
        try {
            existingUser = userDao.getList(filter).stream().findFirst().orElse(null);
            if (existingUser != null) {
                return false;
            }
            userDao.add(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    public List<PianoEntity> getPianos(UserEntity userEntity) throws ServiceException {
        Filter filter = new Filter();
        if (userEntity.getRole() == RoleEntity.Customer) {
            filter.setCustomerId(userEntity.getId());
        }

        try {
            return pianoDao.getList(filter);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void savePiano(PianoEntity piano) throws ServiceException {
        try {
            pianoDao.add(piano);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void saveToCart(UserEntity user, List<CartEntity> carts, String cart) throws ServiceException {
        try {
            for (CartEntity _cart : carts) {
                cartDao.add(_cart);
            }

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


    public List<CartEntity> getCarts(PianoEntity piano, String id) throws ServiceException {
        Filter filter = new Filter();
        filter.setPianoId(piano.getId());
        filter.setCustomerId(Integer.valueOf(id));

        try {
            return cartDao.getList(filter);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void deletePiano(int pianoId) throws ServiceException {
        PianoEntity piano = new PianoEntity();
        piano.setId(pianoId);

        try {
            pianoDao.delete(piano);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<UserEntity> getPiano(String pianoId) throws ServiceException {
        Filter filter = new Filter();
        filter.setPianoId(Integer.parseInt(pianoId));
        try {
            return userDao.getList(filter);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
