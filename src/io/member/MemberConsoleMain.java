package io.member;

import io.member.impl.DataMemberRepository;
import io.member.impl.FileMemberRepository;
import io.member.impl.MemoryMemberRepository;
import io.member.impl.ObjectMemberRepository;

import java.util.Scanner;

public class MemberConsoleMain {

    //    private static final MemberRepository memberRepository = new MemoryMemberRepository();
//    private static final MemberRepository memberRepository = new FileMemberRepository();
//    private static final MemberRepository memberRepository = new DataMemberRepository();
    private static final MemberRepository memberRepository = new ObjectMemberRepository();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. 회원등록 | 2. 회원목록조회 | 3. 종료");
            System.out.print("선택: ");
            int choice = sc.nextInt();
            sc.nextLine(); // new line 제거


            switch (choice) {
                case 1:
                    // 회원 등록
                    registerMember(sc);
                    break;
                case 2:
                    displayMembers();
                    break;
                case 3:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 입력하세요.");
            }
        }
    }

    private static void registerMember(Scanner scanner) {
        System.out.print("ID 입력: ");
        String id = scanner.nextLine();

        System.out.print("Name 입력: ");
        String name = scanner.nextLine();

        System.out.print("Age 입력: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        Member member = new Member(id, name, age);
        memberRepository.add(member);

        System.out.println("회원이 성공적으로 등록되었습니다.");
    }

    private static void displayMembers() {
        System.out.println("회원목록:");
        for (Member member : memberRepository.findAll()) {
            System.out.printf("[ID: %s, Name: %s, Age: %d]\n",
                    member.getId(), member.getName(), member.getAge());
        }
    }
}
