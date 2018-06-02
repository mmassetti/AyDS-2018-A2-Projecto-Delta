package services;

public class ServiceModule {
        private static ServiceModule instance;
        private Service service;

        private ServiceModule() {
            service = new ServiceImp();
        }

        public static ServiceModule getInstance() {
            if (instance == null)
                instance = new ServiceModule();
            return instance;
        }

        public Service getService(){
            return service;
        }
}
