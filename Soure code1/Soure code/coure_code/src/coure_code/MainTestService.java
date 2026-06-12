package coure_code;


import javax.swing.*;

public class MainTestService extends JFrame {
    
    private ServiceManager serviceManager = new ServiceManager();

    public MainTestService() {
        setTitle("He Thong Quan Ly Khach San - Test MVC");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        add(createScreen());
    }

    private JPanel createScreen() {
        ServiceManagementView serviceView = new ServiceManagementView();
        
        new ServiceController(serviceManager, serviceView);
        
        return serviceView;
    }

   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	MainTestService frame = new MainTestService();
            frame.setVisible(true); 
        });
    }
}