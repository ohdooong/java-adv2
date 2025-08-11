package network.tcp.autocloseable;

public class ResourceCloseMainV4 {
    public static void main(String[] args) {
        try {
            logic();
        } catch (CallException e) {
            System.out.println("call exception 예외 처리");
            throw new RuntimeException(e);
        } catch (CloseException e) {
            System.out.println("close exception 예외 처리");
            throw new RuntimeException(e);
        }
    }

    private static void logic() throws CallException, CloseException {
        try (ResourceV2 resource1 = new ResourceV2("resource1");
             ResourceV2 resource2 = new ResourceV2("resource2");
        ) {
            resource1.call();
            resource2.callEx();
        } catch (CallException e) {
            System.out.println("ex: " + e);
            throw e;
        }
    }
}
