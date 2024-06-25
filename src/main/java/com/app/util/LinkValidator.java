package com.app.util;

public final class LinkValidator {
    public static boolean isValidLink(String link) {
        if (link.isEmpty()) return false;
        else if (link.indexOf('/') == -1) return false;
        link = link.replace("//", "/").trim();
        String[] splittedLink = link.split("/");

        if (splittedLink.length >= 3) {
            if (!splittedLink[0].equals("https:")) return false;
            return switch (splittedLink[1]) {
                case "music.yandex.ru" -> isValidYandexMusicLink(splittedLink);
                case "music.apple.com" -> isValidAppleMusicLink(splittedLink);
                case "www.youtube.com" -> true;
                default -> false;
            };
        } else return false;
    }

    private static boolean isValidYandexMusicLink(String[] splittedLink) {
        return splittedLink[2].equals("album") && splittedLink[4].equals("track");
    }

    private static boolean isValidAppleMusicLink(String[] splittedLink) {
        return splittedLink[2].equals("us") && splittedLink[3].equals("album");
    }
}
