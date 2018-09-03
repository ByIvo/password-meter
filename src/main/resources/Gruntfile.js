module.exports = function(grunt) {
	grunt.initConfig({
		pkg: grunt.file.readJSON('package.json'),
		clean: {
			stylesheets: {
				src: 'public/css/*.css'
			}
		},

		less: {
			build: {
				src: 'public/css/<%= pkg.name %>.less',
				dest: 'public/css/<%= pkg.name %>.css'
			}
		},

		watch: {
			options: {
				livereload: true
			},

			stylesheets: {
				files: 'public/css/*.less',
				tasks: ['dist']
			}
		}
	});

grunt.loadNpmTasks('grunt-contrib-watch');
grunt.loadNpmTasks('grunt-contrib-clean');
grunt.loadNpmTasks('grunt-contrib-less');

grunt.registerTask('default', ['watch']);
grunt.registerTask('dist', ['clean:stylesheets', 'less:build']);

};