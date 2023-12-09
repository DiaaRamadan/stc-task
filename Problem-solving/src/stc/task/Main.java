package stc.task;

public class Main {
    public static void main(String[] args) {
        System.out.println(reverse("abd(jnb)asdf"));
        System.out.println(reverse("abd(bnj)asdf"));
        System.out.println(reverse("abdjnbasdf"));
        System.out.println(reverse("ab(dj)n(ba)sdf"));
        System.out.println(reverse("ab(dj)n(basdf"));
        System.out.println(reverse("ab(dj)n)basdf"));
        System.out.println(reverse("(abdjnbasdf)"));
        System.out.println(reverse("(abdjnbasdf"));
        System.out.println(reverse("abdjnbasdf)"));
    }

    public static String reverse(String str) {
        if (str.isEmpty()) return str;
        StringBuilder stringBuilder = new StringBuilder();
        int startPosition = -1;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(')
                startPosition = i;
            else if (str.charAt(i) == ')' && startPosition != -1) {

                StringBuilder reversedSubString = new StringBuilder(str.substring(startPosition + 1, i)).reverse();

                stringBuilder.append(str, 0, startPosition + 1)
                        .append(reversedSubString).append(str.substring(i));
                str = stringBuilder.toString();

                stringBuilder.setLength(0);
                startPosition = -1;

            }
        }
        return str;
    }
}