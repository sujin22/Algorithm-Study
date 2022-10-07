package test.line;

public class s2 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(2, new String[]{"slang", "badword"},
                "badword ab.cd bad.ord .word sl.. bad.word"));
    }
    static class Solution {
        public String solution(int k, String[] dic, String chat) {
            StringBuilder answer = new StringBuilder();
//            StringBuilder result = new StringBuilder();
            //공백으로 단어 나누기
            String[] chatArr = chat.split(" ");
            boolean[] isSlang = new boolean[chatArr.length];
            String[] result = new String[chatArr.length];
            StringBuilder sb;
            loop:
            for(int i =0 ;i<chatArr.length; i++){
                String ch = chatArr[i];
                for(String slang: dic){
                    sb = new StringBuilder();
                    int lastSameIdx = 0;
                    int pointLength = 0;
                    boolean isPoint = false;

                    int slangIdx = 0, chatIdx = 0;
                    while(chatIdx<ch.length()){
                        while(slangIdx<slang.length() &&
                                ch.charAt(chatIdx) != slang.charAt(slangIdx)){
                            slangIdx++;
                        }

                        if(slangIdx >= slang.length() || ch.charAt(chatIdx) == slang.charAt(slangIdx)){
                            if(isPoint && slangIdx - lastSameIdx < pointLength * k){
                                sb.append("#".repeat(pointLength));
                                isPoint = false;
                            }
                            sb.append(ch.charAt(chatIdx));
                            lastSameIdx = slangIdx;
                            chatIdx++;
                            slangIdx = (slangIdx+1 < slang.length())? slangIdx+1: slangIdx;
                        } else if(ch.charAt(chatIdx)=='.'){
                            while(chatIdx<ch.length()
                                    && ch.charAt(chatIdx)=='.'){
                                pointLength++;
                                chatIdx++;
                            }
                            isPoint = true;
                        }else if(ch.charAt(chatIdx) != slang.charAt(slangIdx)){
                            break; //탐색 종료
                        }
                    }
                    if(sb.toString().equals(slang)) {
                        result[i] = "#".repeat(ch.length());
                        isSlang[i] = true;
                    }
                }
                if(isSlang[i]){
                    answer.append(result[i]+" ");
                }else{
                    answer.append(ch+" ");
                }

            }
            return answer.toString();
        }
    }
}
