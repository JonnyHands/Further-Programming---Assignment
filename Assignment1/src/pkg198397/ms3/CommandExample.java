package pkg198397.ms3;

 
public class CommandExample
        
{
    Parser parser = new Parser();
    public CommandExample() {
        
    }
    
    private void execute(Command c) {
        
        switch(c.getCommand()) {
            case STEP: case MARK: 
                System.out.println(c);
                break;
            case QUIT: System.out.println(c.getMsg());
                break;
            default:
                System.out.println(c);
            }
            printPrompt(c.getMsg());
        }
    
    private void commandLine() {
        printPrompt("New Game");
        Command c = parser.getCommand();
        while(c.getCommand() != CommandWord.QUIT) {
            execute(c);
            c = parser.getCommand();
        }
    }
    private void printPrompt(String msg) {
        System.out.println(msg);
        System.out.print(">");
    }
public static void main(String args[]) {
    CommandExample ct = new CommandExample();
    ct.commandLine();
}
}
