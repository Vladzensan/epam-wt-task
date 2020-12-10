package dao;

import entity.PianoEntity;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PianoDao implements Dao<PianoEntity>{
    @Override
    public void add(PianoEntity pianoEntity) throws DaoException {
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            pool = ConnectionPool.getConnectionPool();
            connection = pool.getConnection();

            String sql = "INSERT INTO cart (name, piano_id, price) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, pianoEntity.getName());
            statement.setInt(2, pianoEntity.getPianoId());
            statement.setInt(3, pianoEntity.getPrice());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                pianoEntity.setId(Integer.parseInt(generatedKeys.getString(1)));
            }
        } catch (SQLException | ConnectionException e) {
            throw new DaoException(e);
        } finally {
            if (pool != null)
                pool.returnConnection(connection);
        }
    }

    @Override
    public PianoEntity getById(int id) throws DaoException {
        return null;
    }

    @Override
    public List<PianoEntity> getList(Filter filter) throws DaoException {
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            pool = ConnectionPool.getConnectionPool();
            connection = pool.getConnection();

            PreparedStatement statement;
            if (filter.existTestId() && filter.existCustomerId()) {
                String sql = "SELECT * FROM pianos p";
                statement = connection.prepareStatement(sql);
            } else {
                String sql = "SELECT * FROM  pianos p";
                statement = connection.prepareStatement(sql);
            }

            ResultSet resultSet = statement.executeQuery();
            LinkedList<PianoEntity> pianos = new LinkedList<PianoEntity>();
            while (resultSet.next()) {
                int id = resultSet.getInt("piano.id");
                String text = resultSet.getString("piano.name");
                int price = resultSet.getInt("piano.price");

                PianoEntity piano = new PianoEntity();
                piano.setPianoId(id);
                piano.setName(text);
                piano.setPrice(price);

                pianos.add(piano);
            }

            return pianos;
        } catch (SQLException | ConnectionException e) {
            throw new DaoException(e);
        } finally {
            if (pool != null)
                pool.returnConnection(connection);
        }
    }

    @Override
    public void delete(PianoEntity pianoEntity) throws DaoException {

    }
}
