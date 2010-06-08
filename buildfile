require 'buildr_iidea'
require 'buildr_bnd'
repositories.remote << Buildr::Bnd.remote_repository
require 'buildr/ivy_extension'

CTMS_COMMONS_VERSION = "1.0.0.RELEASE"
CTMS_COMMONS_IVY_ORG = "gov.nih.nci.cabig.ctms"

desc "Shared libraries for caBIG CTMS projects"
define "ctms-commons" do
  project.version = CTMS_COMMONS_VERSION
  project.group = CTMS_COMMONS_IVY_ORG
  project.compile.options.source = "1.5"
  project.iml.excluded_directories << IVY_HOME
  project.iml.group = true

  desc "Zero-dependency common code for all other packages"
  define "base" do
    ivy.compile_conf('compile').compile_type('jar').test_conf('unit-test').test_type('jar')
    package(:bundle).tap do |bundle|
      bundle["Export-Package"] = bnd_export_package
    end
  end

  define "lang" do
    ivy.compile_conf('compile').compile_type('jar').test_conf('unit-test').test_type('jar')
    interproject_dependencies << 'base'

    package(:bundle).tap do |bundle|
      bundle["Export-Package"] = bnd_export_package
    end
  end

  define "testing" do
    project.no_iml

    define "unit" do
      project.iml.group = true
      ivy.compile_conf('compile').compile_type('jar').test_conf('unit-test').test_type('jar')
      interproject_dependencies << 'ctms-commons:lang'
      package(:jar)
    end

    define "uctrace" do
      project.iml.group = true
      ivy.compile_conf('compile').compile_type('jar').test_conf('unit-test').test_type('jar')
      interproject_dependencies << 'ctms-commons:base'
      package(:jar)
    end
  end

  define "core" do
    ivy.compile_conf('compile').compile_type('jar').test_conf('unit-test').test_type('jar')
    interproject_dependencies << 'base' << 'lang' << 'testing:unit'

    package(:bundle).tap do |bundle|
      bundle["Export-Package"] = bnd_export_package
    end
  end

  define "laf" do
    # TODO: deploy and run the demo, if anyone's still using it
    ivy.compile_conf('compile').compile_type('jar').test_conf('unit-test').test_type('jar')
    interproject_dependencies << 'web'

    package(:bundle).tap do |bundle|
      bundle["Export-Package"] = bnd_export_package
    end
  end

  define "web" do
    ivy.compile_conf('compile').compile_type('jar').test_conf('unit-test').test_type('jar')
    interproject_dependencies << 'base' << 'lang' << 'core' << 'testing:unit'

    package(:bundle).tap do |bundle|
      bundle["Export-Package"] = bnd_export_package
    end
  end

  define "acegi" do
    project.no_iml

    define "acl-dao", :base_dir => _('acegi-acl-dao') do
      project.iml.group = true
      ivy.compile_conf('compile').compile_type('jar').test_conf('unit-test').test_type('jar')
      package(:jar)
    end

    define "csm", :base_dir => _('acegi-csm') do
      project.iml.group = true
      ivy.compile_conf('compile').compile_type('jar').test_conf('unit-test').test_type('jar')

      package(:bundle).tap do |bundle|
        bundle["Export-Package"] = bnd_export_package
      end
    end

    define "csm-test", :base_dir => _('acegi-csm-test') do
      project.iml.group = true
      ivy.compile_conf('compile').compile_type('jar').test_conf('unit-test').test_type('jar')
      interproject_dependencies << 'acegi:csm'
    end

    define "grid", :base_dir => _('acegi-grid') do
      project.iml.group = true
      ivy.compile_conf('compile').compile_type('jar').test_conf('unit-test').test_type('jar')
      interproject_dependencies << 'acegi:csm'
      package(:jar)
    end
  end

  define "suite" do
    project.no_iml
    project.version = "0.0.0.RELEASE"

    define "authorization" do
      project.iml.group = true
      ivy.compile_conf('compile').compile_type('jar').test_conf('unit-test').test_type('jar')
      interproject_dependencies << 'ctms-commons:core' << 'ctms-commons:base'
      package(:bundle).tap do |bundle|
        bundle["Export-Package"] = bnd_export_package
      end

      task "test:wipe_db" => task("test:resources") do
        # TODO: this assumes many things: that the database is local, that it is postgresql, etc.
        db_name = File.read(_(:target, :test, :resources, 'csm-connection.properties')).split("\n").
          grep(/connection.url/).first.scan(/jdbc:postgresql:(\S+)/).first.first
        raise "Couldn't find database name" unless db_name
        %w(schema seeddata).each do |n|
          info "Executing #{n}.sql on #{db_name}"
          system("psql '#{db_name}' -F '/' -f '#{_(:target, :test, :resources, "csm-sql/postgresql/#{n}.sql")}'")
        end
      end
    end
  end

  # The following submodules exist but are not part of the
  # whole-project build-release process.  This should be fixed at some
  # point.
  #
  # * ccts-websso-ui
  # * grid
  # * acegi/acegi-csm-testapp
end
