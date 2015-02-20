require "fileutils"
require "net/http"
require "uri"

# get file path, make it run portably
portablePath = File.expand_path(File.dirname(__FILE__))

filesDir = portablePath

puts "current dir is: " + filesDir

srcFile = filesDir + "/MultipleMadnessFiles.txt"

f = File.open(srcFile).read

def open(url)
	Net::HTTP.get(URI.parse(url))
end

outputDir = filesDir + "/outputFiles"

# puts the lines of the file out
f.each_line do |line|

	puts "Line to be written: #{line}"
	content = open(line)
	outputFileName = line.gsub(/http:\/\//, '')
	outputFileName = outputFileName.gsub(/https:\/\//, '')
	outputFileName = outputFileName.gsub(/\/get\//, '')
	outputFileName = outputFileName.gsub(/([\?\-\&\=\*])/, '_')
	outputFileName = outputFileName.strip
	outputFileName = outputDir + '/' + outputFileName
	puts 'OUT::::' + outputFileName

	if outputFileName[-1] == '/'
		outputFileName = outputFileName[0..-2]
	end
	
	baseDirectory = File.dirname( outputFileName )
	FileUtils.mkdir_p( baseDirectory )
	outputFile = File.open(outputFileName, 'w')
	outputFile << content
	outputFile.close
end	


puts "Outputting to: " + outputDir
