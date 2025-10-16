package java412.galleryapp.utils;

public class PaginationUtils {

    public static int[] calculatePageRange(int currentPage, int totalPages, int maxPagesToShow) {

        int startPage = Math.max(0, currentPage - maxPagesToShow / 2);
        int endPage = Math.min(totalPages - 1, startPage + maxPagesToShow - 1);

        if (endPage - startPage < maxPagesToShow - 1) {
            startPage = Math.max(0, endPage - maxPagesToShow + 1);
        }

        return new int[]{startPage, endPage};

    }

}
