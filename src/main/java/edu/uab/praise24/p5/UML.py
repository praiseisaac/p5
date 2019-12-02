import os
import time
lineCount = 0

outfile = open('UML doc.txt','w')
classname = ''
for filename in os.listdir('.'):
	if filename.find('.java') != -1:
		print(filename)
		fileread = open(filename,'r')
		for line in fileread:
			if line.find('public class') != -1:
				outfile.write('\n')
				outfile.write('Class name:  ' + line[line.find('public class') + len('public class '):line.find('{')] + '\n')
				classname = line[line.find('public class') + len('public class '):-1]
			elif line.find('public interface') != -1:
				outfile.write('\n')
				outfile.write('interface name:  ' + line[line.find('interface ') + len('interface '):line.find('{')] + '\n')
				classname = line[line.find('public class') + len('public class '):-1]
			elif line.find('public abstract class') != -1:
				outfile.write('\n')
				outfile.write('Class name (abstract):  ' + line[line.find('class') + len('class '):line.find('{')] + '\n')
				classname = line[line.find('public abstract class') + len('public abstract class '):-1]
			elif line.find('public') != -1 and line.find(classname) != -1:
				outfile.write('+ ' + line[line.find('public ') + len('public '):line.find('{')] + '\n')
			elif line.find('public void') != -1 or line.find('private void') != -1 and line.find('(') != -1 and line.find('static') == -1 and line.find('<>') == -1:
				if line.find('public') != -1:
					outfile.write('+ ' + line[line.find('public void') + len('public void '):line.find('{')] + '\n')
				elif line.find('private') != -1:
					outfile.write('- ' + line[line.find('private void ') + len('private void '):line.find('{')] + '\n')
			elif line.find('public') != -1 or line.find('private') != -1 and line.find('(') != -1 and line.find('static') == -1 and line.find('<>') == -1:
				if line.find('public') != -1:
					outfile.write('+ ' + line[line.find('public ') + len('public '):line.find('{')] + '\n')
				elif line.find('private') != -1:
					outfile.write('- ' + line[line.find('private ') + len('private '):line.find('{')] + '\n')
                        lineCount = lineCount + 1
		fileread.close()
outfile.close()
print(lineCount)
