class Solution {
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<>();

        for (String path : paths) {
            String[] parts = path.split(" ");
            String directory = parts[0];

            for (int i = 1; i < parts.length; i++) {
                String file = parts[i];

                int open = file.indexOf('(');
                int close = file.indexOf(')');

                String fileName = file.substring(0, open);
                String content = file.substring(open + 1, close);

                map.computeIfAbsent(content, k -> new ArrayList<>())
                   .add(directory + "/" + fileName);
            }
        }

        List<List<String>> result = new ArrayList<>();

        for (List<String> files : map.values()) {
            if (files.size() > 1) {
                result.add(files);
            }
        }

        return result;
    }
}