package com.automation.ui.base.common.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class TestUtils {

    public static void sleep(final long millis) throws IllegalArgumentException {
        try {
            Thread.sleep(millis);
        } catch (final InterruptedException e) {
        }
    }

    public static int randomBetween(final int min, final int max) {

        if (min > max) {
            throw new IllegalArgumentException("The minimum number is greater than the maximal number!");
        }

        final Random random = new Random();
        return random.nextInt(max - min + 1) + min;

    }

    public static List<Integer> randomBetweenList(final int min, final int max, final int numberOfValues) {

        if (min > max) {
            throw new IllegalArgumentException("The minimum number is greater than the maximal number!");
        }

        if (numberOfValues < 1) {
            throw new IllegalArgumentException("The number of values is smaller than one!");
        }

        final List<Integer> list = new ArrayList();

        int index = 0;
        loop:
        while (index < numberOfValues) {

            final Integer random = Integer.valueOf(randomBetween(min, max));

            for (final Integer number : list) {

                if (number.equals(random) && number != null) {
                    continue loop;
                }

            }

            list.add(random);
            index++;

        }

        return list;

    }

    public static String isDuplicatePresent(final List<String> list) {

        final int size = list.size();

        if (list.isEmpty() || size == 0) {
            throw new NullPointerException("List is empty!");
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (list.get(i).equals(list.get(j)) && i != j) {
                    return list.get(i);
                }
            }
        }

        return null;

    }

    public static boolean isAllEquals(final List<String> list) {

        final int size = list.size();

        if (list.isEmpty() || size == 0) {
            throw new NullPointerException("List is empty!");
        }

        boolean isAllEquals = false;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (list.get(i).equals(list.get(j))) {
                    isAllEquals = true;
                } else {
                    return false;
                }
            }
        }

        return isAllEquals;

    }

    /**
     * Define path for Screenshot file.
     */
    protected String getScreenshotSavePath() {
        String packageName = this.getClass().getPackage().getName();
        //  File dir = new File(System.getProperty("user.dir")+File.separator+"screenshot"+File.separator + packageName + File.separator);
        File dir = new File(System.getProperty("user.dir") + File.separator + "screenshot" + File.separator);

        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir.getAbsolutePath();
    }

    /**
     * Take Screenshot on failure.
     */
    protected void getScreenshot(String methodName) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String date = sdf.format(new Date());
        // String url = driver.getCurrentUrl().replaceAll("[\\/:*\\?\"<>\\|]", "_");

        String ext = ".png";
        //  String path = + File.separator + date + "_" + methodname + ext;
        String path = getScreenshotSavePath() + File.separator + date + "_" + methodName + ext;
        File source = null;
       /* try {

          //  if (driver instanceof TakesScreenshot) {

               // source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                //  org.openqa.selenium.io.FileHandler.copy(tmpFile, new File(path));
               // FileUtils.copyFile(source, new File(path));
             //   logger.error("Captured Screenshot for Failure: " + path);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {

        }*/
    }

}