package com.example.springinaction.repetit.testTask;

import java.io.BufferedReader;
import java.util.*;

import com.example.springinaction.repetit.testTask.Task;
import lombok.Data;

@Data
public class TestList {

        private List<Task> taskList;
        private List<Integer> taskNumberList;
        private TestList() {
            taskList = new ArrayList<>();
            taskNumberList = new ArrayList<>();

            Task singleAnswerTask = new Task();
            singleAnswerTask.setQuestion("Свойства каких частей речи в себе соединило причастие?");
            List<String> answersList = new ArrayList<>();
            answersList.add("Глагола и имени существительного");
            answersList.add("Глагола и имени прилагательного");
            answersList.add("Наречия и глагола");
            singleAnswerTask.setType(Task.Type.SingleAnswer);
            singleAnswerTask.setId(0);
//            singleAnswerTask.setResult("no");
            singleAnswerTask.setCorrectAnswer("Глагола и имени прилагательного");
            singleAnswerTask.setAnswersList(answersList);


            Task singleAnswerTask2 = new Task();
            singleAnswerTask2.setQuestion("Выберите из приведённых вариантов ответа причастие");
            List<String> answersList2 = new ArrayList<>();
            answersList2.add("Идя");
            answersList2.add("Лежавший");
            answersList2.add("Горячо");
            singleAnswerTask2.setType(Task.Type.SingleAnswer);
            singleAnswerTask2.setId(1);
//            singleAnswerTask2.setResult("no");
            singleAnswerTask2.setCorrectAnswer("Лежавший");
            singleAnswerTask2.setAnswersList(answersList2);



            Task multyAnswerTask = new Task();
            multyAnswerTask.setQuestion("Свойства каких частей речи в себе соединило причастие?");
            List<String> answersList3 = new ArrayList<>();
            answersList3.add("Глагол");
            answersList3.add("Имя прилагательное");
            answersList3.add("Наречие");
            multyAnswerTask.setType(Task.Type.MultyAnswer);
            multyAnswerTask.setId(2);
//            multyAnswerTask.setResult("no");
            multyAnswerTask.setCorrectAnswer("Глагол|Имя прилагательное");
            multyAnswerTask.setAnswersList(answersList3);


            Task multyAnswerTask2 = new Task();
            multyAnswerTask2.setQuestion("Выберите из приведённых вариантов ответа причастие");
            List<String> answersList4 = new ArrayList<>();
            answersList4.add("Идя");
            answersList4.add("Лежавший");
            answersList4.add("Бежавший");
            multyAnswerTask2.setType(Task.Type.MultyAnswer);
            multyAnswerTask2.setId(3);
//            multyAnswerTask2.setResult("no");
            multyAnswerTask2.setCorrectAnswer("Бежавший|Лежавший");
            multyAnswerTask2.setAnswersList(answersList4);

            taskList.add(singleAnswerTask);
            taskList.add(singleAnswerTask2);
            taskList.add(multyAnswerTask);
//            taskList.add(multyAnswerTask2);

            taskNumberList.add(1);
            taskNumberList.add(2);
            taskNumberList.add(3);
//            taskNumberList.add(4);

        }


        private static class TestListHolder {
            public static final TestList HOLDER_INSTANCE = new TestList();
        }

        public static TestList getInstance() {
            return TestListHolder.HOLDER_INSTANCE;
        }

        public static ArrayList getQuestionList(int count){
            System.out.println("1");
            TestList list = TestList.getInstance();
            System.out.println("11");
            ArrayList<Task> questionList = new ArrayList<>();
            System.out.println("111");
            Set<Integer> idSet = new HashSet<>();
            System.out.println("1111");
            int max = 3;
            Random random = new Random();
            int randomNum;
            while (idSet.size() < count) {
                randomNum = random.nextInt(max);
                if (!idSet.contains(randomNum)){
                    idSet.add(randomNum);
                    System.out.println("11111");
                    questionList.add(list.getTaskList().get(randomNum).clone());
                    System.out.println("1111111");
                }
            }
            return questionList;
        }
    @Override
    public String toString() {
        String res = "";
        for (Task task:
             taskList) {
            res = res + task.toString() + "\n";
        }
        return res;
    }

}
