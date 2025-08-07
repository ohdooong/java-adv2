package network.tcp.autocloseable;

public class ResourceCloseMainV2 {
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
        ResourceV1 resource1 = null;
        ResourceV1 resource2 = null;
        try {
            resource1 = new ResourceV1("resource1");
            resource2 = new ResourceV1("resource2");

            resource1.call();
            resource2.callEx();
        } catch (CallException e) {
            throw e;
        } finally {
            if (resource2 != null) {
                resource2.closeEx();
            }
            if (resource1 != null) {
                resource1.closeEx(); // 이 코드 호출 안됨
            }
        }

    }
}
