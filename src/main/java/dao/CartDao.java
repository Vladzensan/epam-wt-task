package dao;

import entity.CartEntity;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CartDao implements Dao<CartEntity>{

    @Override
    public void add(CartEntity cartEntity) throws DaoException {
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            pool = ConnectionPool.getConnectionPool();
            connection = pool.getConnection();

            String sql = "INSERT INTO cart (text, piano_id, cart_id) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, cartEntity.getText());
            statement.setInt(2, cartEntity.getPiano().getPianoId());
            statement.setInt(3, cartEntity.getCartId());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                cartEntity.setId(Integer.parseInt(generatedKeys.getString(1)));
            }
        } catch (SQLException | ConnectionException e) {
            throw new DaoException(e);
        } finally {
            if (pool != null)
                pool.returnConnection(connection);
        }
    }

    @Override
    public CartEntity getById(int id) throws DaoException {
        return null;
    }

    @Override
    public List<CartEntity> getList(Filter filter) throws DaoException {
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            pool = ConnectionPool.getConnectionPool();
            connection = pool.getConnection();

            PreparedStatement statement;
            if (filter.existTestId() && filter.existCustomerId()) {
                String sql = "SELECT * FROM pianos p, cart c WHERE p.text_id = ? AND c.piano_id = ? AND p.id = c.id";
                statement = connection.prepareStatement(sql);
                statement.setInt(2, filter.getCustomerId());
            } else {
                String sql = "SELECT * FROM questions q, answers a WHERE q.id = a.question_id";
                statement = connection.prepareStatement(sql);
            }

            ResultSet resultSet = statement.executeQuery();
            LinkedList<CartEntity> carts = new LinkedList<CartEntity>();
            while (resultSet.next()) {
                int id = resultSet.getInt("a.id");
                String text = resultSet.getString("a.text");
                int cartId = resultSet.getInt("q.id");

                CartEntity cart = new CartEntity();
                cart.setText(String.valueOf(id));
                cart.setText(text);

                carts.add(cart);
            }

            return carts;
        } catch (SQLException | ConnectionException e) {
            throw new DaoException(e);
        } finally {
            if (pool != null)
                pool.returnConnection(connection);
        }
    }

    @Override
    public void delete(CartEntity cartEntity) throws DaoException {

    }
}
