package com.example.ed_it_art.clientapplication.model.utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

import com.example.ed_it_art.clientapplication.model.entities.Branch;
import com.example.ed_it_art.clientapplication.model.entities.Car;
import com.example.ed_it_art.clientapplication.model.entities.Client;
import com.example.ed_it_art.clientapplication.model.entities.ModelCar;
import com.example.ed_it_art.clientapplication.model.entities.Order;

import java.util.List;

/**
 * Created by Shiran on 22/01/2018.
 */

public class RentsConst {
    /**
     * CLIENT CONTENT
     */
    public static final String AUTORITY = "com.boukris.RentConst";
    public static final Uri AUTORITY_URI = Uri.parse("content://" + AUTORITY);

    public static class ClientConst {
        public static final String NAME = "name";
        public static final String LAST_NAME = "last_name";
        public static final String ID = "_id";
        public static final String MAIL = "mail";
        public static final String CREDIT_CARD = "credit_number";
        public static final String USER_NAME = "user_name";
        public static final String PASSWORD = "password";

        public static final Uri CLIENT_URI = Uri.withAppendedPath(AUTORITY_URI,"clients");
    }

    public static ContentValues ClientToContentValues(Client client) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(RentsConst.ClientConst.NAME, client.getName());
        contentValues.put(RentsConst.ClientConst.LAST_NAME, client.getLast_name());
        contentValues.put(RentsConst.ClientConst.ID, client.get_id());
        contentValues.put(RentsConst.ClientConst.MAIL, client.getMail());
        contentValues.put(RentsConst.ClientConst.CREDIT_CARD, client.getCredit_number());
        contentValues.put(RentsConst.ClientConst.USER_NAME, client.getUser_name());
        contentValues.put(RentsConst.ClientConst.PASSWORD, client.getPassword());

        return contentValues;
    }

    public static Client ContentValuesToClient(ContentValues contentValues) {
        Client client = new Client();
        client.setName(contentValues.getAsString(RentsConst.ClientConst.NAME));
        client.setLast_name(contentValues.getAsString(RentsConst.ClientConst.LAST_NAME));
        client.set_id(contentValues.getAsLong(RentsConst.ClientConst.ID));
        client.setMail(contentValues.getAsString(RentsConst.ClientConst.MAIL));
        client.setCredit_number(contentValues.getAsLong(RentsConst.ClientConst.CREDIT_CARD));
        client.setUser_name(contentValues.getAsString(RentsConst.ClientConst.USER_NAME));
        client.setPassword(contentValues.getAsString(RentsConst.ClientConst.PASSWORD));
        return client;
    }

    /**
     * CAR CONTENT!!
     */
    public static class CarConst {
        public static final String FIX_BRANCH = "fix_branch";
        public static final String MODEL = "model_car";
        public static final String KILO_METERAJE = "kilo_metrage";
        public static final String CAR_NUMBER = "car_number";

        public static final Uri CAR_URI = Uri.withAppendedPath(AUTORITY_URI,"cars");
    }

    public static ContentValues carToContentValues(Car car) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(RentsConst.CarConst.FIX_BRANCH, car.getFix_branch());
        contentValues.put(RentsConst.CarConst.MODEL, car.getModel_car());
        contentValues.put(RentsConst.CarConst.KILO_METERAJE, car.getKilo_metrage());
        contentValues.put(RentsConst.CarConst.CAR_NUMBER, car.getCar_number().toString());

        return contentValues;
    }

    public static Car ContentValuesToCar(ContentValues contentValues) {
        Car car = new Car();
        car.setFix_branch(contentValues.getAsInteger(CarConst.FIX_BRANCH));
        car.setModel_car(contentValues.getAsString(CarConst.MODEL));
        car.setKilo_metrage(contentValues.getAsDouble(CarConst.KILO_METERAJE));
        car.setCar_number(contentValues.getAsString(CarConst.CAR_NUMBER));
        return car;
    }

    /**
     * BRANCH CONTENT
     */

    public static class BranchConst {
        public static final String ADDRESS = "address_branch";
        public static final String NUMBER_PARKING_LAST = "number_parking";
        public static final String BRANCH_NUMBER = "branch_number";
    }

    public static ContentValues BranchToContentValues(Branch branch) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(RentsConst.BranchConst.ADDRESS, branch.getAddress_branch());
        contentValues.put(RentsConst.BranchConst.NUMBER_PARKING_LAST, branch.getNumber_parking());
        contentValues.put(RentsConst.BranchConst.BRANCH_NUMBER, branch.getBranch_number());

        return contentValues;
    }

    public static Branch ContentValuesToBranch(ContentValues contentValues) {
        Branch branch = new Branch();
        branch.setAddress_branch(contentValues.getAsString(RentsConst.BranchConst.ADDRESS));
        branch.setNumber_parking(contentValues.getAsInteger(RentsConst.BranchConst.NUMBER_PARKING_LAST));
        branch.setBranch_number(contentValues.getAsInteger(RentsConst.BranchConst.BRANCH_NUMBER));
        return branch;
    }

    /**
     * MODEL CAR CONTENT!!!!!!
     */

    public static class ModelCarConst {
        public static final String MODEL_NAME = "model_name";
        public static final String YEAR_CAR = "year_car";
        public static final String GEAR_BOX = "gear_box";
        public static final String CHAIRS = "chairs";
        public static String MOTOR_CAPACITY = "motor_capacity";
    }

    public static ContentValues ModelCarToContentValues(ModelCar modelCar) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(RentsConst.ModelCarConst.MODEL_NAME, modelCar.getModelName());
        contentValues.put(ModelCarConst.YEAR_CAR, modelCar.getYear_car());
        contentValues.put(RentsConst.ModelCarConst.MOTOR_CAPACITY, modelCar.getMotor_capacity());
        contentValues.put(RentsConst.ModelCarConst.GEAR_BOX, modelCar.isGear_box());
        contentValues.put(RentsConst.ModelCarConst.CHAIRS, modelCar.getChairs());

        return contentValues;
    }

    public static ModelCar ContentValuesToModelCar(ContentValues contentValues) {
        ModelCar modelCar = new ModelCar();
        modelCar.setModel_name(contentValues.getAsString(RentsConst.ModelCarConst.MODEL_NAME));
        modelCar.setYear_car(contentValues.getAsInteger(ModelCarConst.YEAR_CAR));
        modelCar.setMotor_capacity(contentValues.getAsDouble(RentsConst.ModelCarConst.MOTOR_CAPACITY));
        modelCar.setGear_box(contentValues.getAsBoolean(RentsConst.ModelCarConst.GEAR_BOX));
        modelCar.setChairs(contentValues.getAsInteger(RentsConst.ModelCarConst.CHAIRS));
        return modelCar;
    }

    public static class OrdersConst {
        public static final String ID_CLIENT = "id_client";
        public static final String ORDER_OPEN = "order_open";
        public static final String NUMBER_CAR = "number_car";
        public static final String CAR_RENTAL_SATRT = "car_rental_start";
        public static final String CAR_RENTAL_END = "car_rental_end";
        public static final String START_KM = "start_km";
        public static final String END_KM = "end_km";
        public static final String HAD_FUEL = "had_fuel";
        public static final String AMOUNT_FUEL = "amount_fuel";
        public static final String FINAL_BILLING = "final_billing";
        public static final String NUMBER_ORDER = "number_order";
        public static final String NUMBER_BRANCH = "number_branch";

        public static final Uri ORDER_URI = Uri.withAppendedPath(AUTORITY_URI,"orders");
    }

    public static ContentValues OrderToContentValues(Order order) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(RentsConst.OrdersConst.ID_CLIENT, order.getId_client());
        contentValues.put(RentsConst.OrdersConst.ORDER_OPEN, order.isOrder_open());
        contentValues.put(RentsConst.OrdersConst.NUMBER_CAR, order.getNumber_car());
        contentValues.put(RentsConst.OrdersConst.CAR_RENTAL_SATRT, String.valueOf(order.getCar_rental_start()));
        contentValues.put(OrdersConst.CAR_RENTAL_END, String.valueOf(order.getCar_rental_end()));
        contentValues.put(OrdersConst.START_KM, order.getStart_km());
        contentValues.put(OrdersConst.END_KM, order.getEnd_km());
        contentValues.put(OrdersConst.HAD_FUEL, order.isHad_fuel());
        contentValues.put(OrdersConst.AMOUNT_FUEL, order.getAmount_fuel());
        contentValues.put(OrdersConst.FINAL_BILLING, order.getFinal_billing());
        contentValues.put(OrdersConst.NUMBER_ORDER, order.getNumber_order());
        contentValues.put(OrdersConst.NUMBER_BRANCH, order.getNumber_branch());

        return contentValues;
    }

    public static Order ContentValuesToOrder(ContentValues contentValues) {
        Order order = new Order();
        order.setId_client(contentValues.getAsInteger(OrdersConst.ID_CLIENT));
        order.setOrder_open(contentValues.getAsBoolean(OrdersConst.ORDER_OPEN));
        order.setNumber_car(contentValues.getAsString(OrdersConst.NUMBER_CAR));
        order.setCar_rental_start(contentValues.getAsString(OrdersConst.CAR_RENTAL_SATRT));
        order.setCar_rental_end(contentValues.getAsString(OrdersConst.CAR_RENTAL_END));
        order.setStart_km(contentValues.getAsDouble(OrdersConst.START_KM));
        order.setEnd_km(contentValues.getAsDouble(OrdersConst.END_KM));
        order.setHad_fuel(contentValues.getAsBoolean(OrdersConst.HAD_FUEL));
        order.setAmount_fuel(contentValues.getAsDouble(OrdersConst.AMOUNT_FUEL));
        order.setFinal_billing(contentValues.getAsDouble(OrdersConst.FINAL_BILLING));
        order.setNumber_branch(contentValues.getAsInteger(OrdersConst.NUMBER_ORDER));
        order.setNumber_order(contentValues.getAsInteger(OrdersConst.NUMBER_BRANCH));
        return order;
    }


    /***
     * manager:
     */

    public static class ManageConst {
        public static final String MANAGER_NAME = "manager_name";
        public static final String MANAGER_PASSWORD = "manager_password";
    }
/*

    public static ContentValues ManagerToContentValues(Manager manager) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(RentsConst.ManageConst.MANAGER_NAME, manager.getUserName());
        contentValues.put(ManageConst.MANAGER_PASSWORD, manager.getPassword());
        return contentValues;
    }

    public static Manager ContentValuesToManager(ContentValues contentValues) {
        Manager manager = new Manager();
        manager.setUserName(contentValues.getAsString(ManageConst.MANAGER_NAME));
        manager.setPassword(contentValues.getAsString(ManageConst.MANAGER_PASSWORD));
        return manager;
    }
*/

    /***
     * client
     * @param {@link} client
     * @return Cursors
     */
    public static Cursor ClientListToCursor(List<Client> students) {
        String[] columns = new String[]
                {
                        ClientConst.NAME,
                        ClientConst.LAST_NAME,
                        ClientConst.ID,
                        ClientConst.MAIL,
                        ClientConst.CREDIT_CARD,
                        ClientConst.USER_NAME,
                        ClientConst.PASSWORD

                };

        MatrixCursor matrixCursor = new MatrixCursor(columns);

        for (Client client : students) {
            matrixCursor.addRow(new Object[]
                    {
                            client.getName(),
                            client.getLast_name(),
                            client.get_id(),
                            client.getMail(),
                            client.getCredit_number(),
                            client.getUser_name(),
                            client.getPassword()
                    });
        }

        return matrixCursor;
    }

    /***
     * cars
     * @param cars
     * @return list
     */
    public static Cursor carListToCursor(List<Car> cars) {
        String[] column = new String[]
                {
                        CarConst.CAR_NUMBER,
                        CarConst.FIX_BRANCH,
                        CarConst.KILO_METERAJE,
                        CarConst.MODEL
                };
        MatrixCursor matrixCursor = new MatrixCursor(column);
        for (Car car : cars) {
            matrixCursor.addRow(new Object[]
                    {
                            car.getCar_number(),
                            car.getFix_branch(),
                            car.getModel_car(),
                            car.getKilo_metrage()

                    });
        }
        return matrixCursor;
    }

    /**
     * carmodel
     * @param modelCars
     * @return
     */
    public static Cursor carModelListToCursor(List<ModelCar>modelCars ) {
        String[] column = new String[]
                {
                        ModelCarConst.CHAIRS,
                        ModelCarConst.MODEL_NAME,
                        ModelCarConst.GEAR_BOX,
                        ModelCarConst.MOTOR_CAPACITY,
                        ModelCarConst.YEAR_CAR
                };
        MatrixCursor matrixCursor = new MatrixCursor(column);
        for (ModelCar modelCar : modelCars) {
            matrixCursor.addRow(new Object[]
                    {
                            modelCar.getYear_car(),
                            modelCar.getModelName(),
                            modelCar.getChairs(),
                            modelCar.getMotor_capacity(),
                            modelCar.isGear_box()

                    });
        }
        return matrixCursor;
    }

    /**
     * branch
     * @param branches
     * @return
     */
    public static Cursor branchListToCursor(List<Branch>branches ) {
        String[] column = new String[]
                {
                        BranchConst.ADDRESS,
                        BranchConst.BRANCH_NUMBER,
                        BranchConst.NUMBER_PARKING_LAST

                };
        MatrixCursor matrixCursor = new MatrixCursor(column);
        for (Branch branch : branches) {
            matrixCursor.addRow(new Object[]
                    {
                            branch.getNumber_parking(),
                            branch.getBranch_number(),
                            branch.getAddress_branch()

                    });
        }
        return matrixCursor;
    }
}
