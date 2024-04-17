package br.com.carstore.dao;
import br.com.carstore.model.Car;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class CarDAO {
    public void createCar(Car car) {
        try {
            String SQL = "INSERT INTO CAR (NAME) VALUES (?)";
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, car.getName());
            preparedStatement.execute();
            System.out.println("Sucesso");
        } catch (Exception e) {
            System.out.println("Erro");
        }

    }
    public List<Car> findAllCars() {
        String SQL = "SELECT * FROM CAR";
        try {

            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Car> cars = new ArrayList<>();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String carName = resultSet.getString("name");

                Car car = new Car(id,carName);
                cars.add(car);
            }
            System.out.println("successo");
            connection.close();
            return cars;
        } catch (Exception e) {
            System.out.println("falha");
            return Collections.emptyList();
        }
    }

    public void deleteCarById(String carId) {

        String SQL = "DELETE CAR WHERE ID = ?";

        try {

            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");

            System.out.println("successo");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, carId);
            preparedStatement.execute();

            System.out.println("successo para deletar: " + carId);

            connection.close();

        } catch (Exception e) {

            System.out.println("falha ao deletar");

        }

    }
}



